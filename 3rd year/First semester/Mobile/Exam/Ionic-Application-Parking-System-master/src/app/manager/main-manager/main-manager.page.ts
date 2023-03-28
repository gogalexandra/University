import { Component, OnInit } from '@angular/core';
import {AlertController, LoadingController, ModalController} from "@ionic/angular";
import {ApiService} from "../../api.service";
import {WebsocketService} from "../../websocket.service";
import {AddSpacePage} from "../add-space/add-space.page";
import {DatabaseService} from "../../database.service";

@Component({
  selector: 'app-main-manager',
  templateUrl: './main-manager.page.html',
  styleUrls: ['./main-manager.page.scss'],
  providers: [WebsocketService]
})
export class MainManagerPage implements OnInit {
  elements: any[] | undefined

  constructor(public modalCtrl: ModalController, private apiService: ApiService, private websocketService: WebsocketService,
              private alertController: AlertController, private loadingCtrl: LoadingController, private db: DatabaseService) {
    this.loadElements();
    websocketService.messages.subscribe(() => {
      this.getAllElements();
    });
  }

  ngOnInit() {
  }

  getAllElements() {
    const elems: any[] | undefined = [];

    this.apiService.getAllElements().subscribe( (returnedElems) => {
      // @ts-ignore
      for (const item of returnedElems) {
        const elem = {
          id: item.id,
          number: item.number,
          address: item.address,
          status: item.status,
          count: item.count,
        }
        elems.push(elem);
      }

        this.elements = elems;
        this.db.setElements(elems).then(() => console.log('Local DB refreshed successfully!'));
    },
      error => {
      this.showAlert('Disconnected from server! You are offline!\n Check your internet connection and refresh page!');
      this.elements = this.db.getAllElements();
    })
    console.log(this.elements)
  }

  loadElements() {
    this.simpleLoader();
    this.getAllElements();
    this.dismissLoader();
  }

  async addElement() {
    const modal = await this.modalCtrl.create({
      component: AddSpacePage
    })
    modal.onDidDismiss().then(() => {
      this.getAllElements();
    })
    return await modal.present();
  }

  async delete(id: string) {
    this.alertController.create({
      message: 'Are you sure you want to delete this?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Delete',
          handler: () => {
            this.apiService.deleteElement(id).subscribe(  {
              error: (error) => {
                this.showAlert('Disconnected from server! You are offline!');
                this.db.deleteElement(id);
                const message = error.error?.error ?? 'Unknown server error';
                console.log(message);
                window.location.reload();
              },
              complete: () => {
                window.location.reload();
              },
            })
          }
        }
      ]
    }).then(res => {
      res.present();
    });
  }

  showAlert(message: string) {
    this.alertController.create({
      header: 'Info',
      message: message,
      buttons: ['OK']
    }).then(res => {
      res.present();
    });
  }

  simpleLoader() {
    this.loadingCtrl.create({
      duration: 700,
      message: 'Loading...'
    }).then((response) => {
      response.present();
    });
  }

  dismissLoader() {
    this.loadingCtrl.dismiss().then((response) => {
      console.log('Loader closed!', response);
    }).catch(() => {
      console.log();
    });
  }
}
