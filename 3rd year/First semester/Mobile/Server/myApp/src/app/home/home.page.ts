import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { AlertController, NavController } from '@ionic/angular';
import { MatDialog } from '@angular/material/dialog';
import { TaskService, Task } from '../services/task.service';
import { Subscription } from 'rxjs';
import { TaskFormComponent } from '../task-form/task-form.component';


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit{

  type = 'task';
  tasks: Array<Task> = [];
  subscription: Subscription = new Subscription();
  subscription2: Subscription = new Subscription();
  public data = {id: 0, name: "", info: "", duration: 0.00,  isDone: 0}
  public childTask: any;
  isDialogOpen: Boolean = false;

  constructor(private taskService: TaskService, private alertCtrl: AlertController, public dialog: MatDialog, public navCtrl: NavController, public router: ActivatedRoute){
    // this.router.queryParams.subscribe(params => {
    //   console.log(this.data)
    //   this.data = JSON.parse(params["data_2"]);
    // });
  }

  ngOnInit(): void {
    this.subscribeTasks();
    //this.subscription2 = this.taskService.currentData.subscribe(message => this.data = message)
    // this.router.queryParams.subscribe(params => {
    //   this.data = JSON.parse(params["data"]);
    //   console.log(this.data)
    // });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    //this.subscription2.unsubscribe();
  }

  subscribeTasks(): void {
    this.subscription = this.taskService.getTasks().subscribe((tasks) => {
      this.tasks = tasks;
    });
  }

  async presentAlert(i: Task) {
    const alert = await this.alertCtrl.create({
      header: 'Confirmation',
      message: 'Are you sure you want to permanently delete this user?',
      buttons: [{
                      text: 'No',
                      handler: () => {
                        console.log('Cancel clicked');
                      }
                },
                  {
                      text: 'Yes',
                      handler: () => {
                        this.taskService.deleteTask(i.id).subscribe(()=>{
                          let index = this.tasks.findIndex(x => x.id === i.id);
                          this.tasks.splice(index,1)})
                      }
                  }],
    });

    await alert.present();
  }

  public openDialog(): void{
    this.isDialogOpen = true
    const dialogRef = this.dialog.open(TaskFormComponent, {
      width: "100%",
      autoFocus: true,
      data: undefined,
    });

    dialogRef.afterClosed().subscribe(result => {
      this.taskService.addTask({...result, isDone: result.isDone === 'true' ? true:false});
      this.isDialogOpen = false
    });
  }

  public openDialog2(task: Task): void{
    this.isDialogOpen = true;
    const dialogRef = this.dialog.open(TaskFormComponent, {
      width: "100%",
      autoFocus: true,
      data:  {name: task.name, info: task.info, duration: task.duration, isDone: task.isDone}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.isDialogOpen = false;
      //this.taskService.updateTask(this.taskService.getIdofTask(task), {...result, isDone: result.isDone === 'true' ? true:false});
    });
  }

  public goToForm(){
    this.navCtrl.navigateForward(['/form']);
  }

  public goToFormWithParams(task: Task){
    let navigationExtras: NavigationExtras = {
      queryParams: {
          data: JSON.stringify(task),
      }
    };

    this.navCtrl.navigateForward(['/form'], navigationExtras);
    //this.subscribeTasks();
  }
  
  // childToParent(task: Task){
  //   this.childTask=task;
  //   }
  
}
