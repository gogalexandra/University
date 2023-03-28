import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { JavaHttpService } from '../http/java-http.service';
import { TasksResponse } from '../model/tasksResponse';
import { map } from 'rxjs/operators';
import { addTaskRequest } from '../model/addTaskRequest';
import { updateTaskRequest } from '../model/updateTaskRequest';
import { webSocket } from "rxjs/webSocket";
import { initialize } from '@ionic/core';


export interface Task {
  id: number;
  name: string;
  info: string;
  duration: number;
  isDone: number;
}

@Injectable({
  providedIn: 'root'
})
export class TaskService{

  tasks: BehaviorSubject<Array<Task>> = new BehaviorSubject(new Array());
  public data: Task = {id: 0, name: "", info: "", duration: 0.00,  isDone: 0}
  private newTask = new BehaviorSubject(this.data);
  currentData = this.newTask.asObservable();

  constructor(public httpService: JavaHttpService) {    
  }

  getTasks(): Observable<Array<Task>> {
      return this.httpService
      .get<TasksResponse>(`/task/all`)
      .pipe(
        map((tasksResponse) => {
          return tasksResponse.items;
        })
      )
      // .subscribe(tasksResponse => {
      //   this.tasks.next(tasksResponse);
      // });
  }

  public addTask(taskRequest: addTaskRequest): Observable<Task> {
    return this.httpService.post<Task, addTaskRequest>(`/task`, taskRequest);
  }

  public updateTask(taskId: number, taskRequest: updateTaskRequest): Observable<Task> {
    return this.httpService.put<Task, updateTaskRequest>(`/task/${taskId}`, taskRequest);
  }

  public deleteTask(id: number): Observable<boolean> {
    return this.httpService.delete<boolean>(`/task/all/${id}`);
  }

  changeTask(task: Task) {
    this.newTask.next(task)
  }

}


