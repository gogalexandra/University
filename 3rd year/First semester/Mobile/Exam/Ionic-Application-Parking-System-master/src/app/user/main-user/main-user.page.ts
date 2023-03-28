import { Component, OnInit } from '@angular/core';
import {AlertController, LoadingController, ModalController} from "@ionic/angular";
import {ApiService} from "../../api.service";
import {WebsocketService} from "../../websocket.service";

@Component({
  selector: 'app-main-user',
  templateUrl: './main-user.page.html',
  styleUrls: ['./main-user.page.scss'],
  providers: [WebsocketService]
})
export class MainUserPage implements OnInit {
  elements: any[] | undefined

  constructor(public modalCtrl: ModalController, private apiService: ApiService, private websocketService: WebsocketService,
              private alertController: AlertController, private loadingCtrl: LoadingController) {
    this.loadElements();
    websocketService.messages.subscribe(() => {
      this.getAllElements();
    });
  }

  ngOnInit() {
  }

  getAllElements() {
    const elems: any[] | undefined = [];
    this.apiService.getFreeElements().subscribe( (returnedElems) => {
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
      elems.sort((a, b) => (a.number > b.number) ? -1 : 1)
    })
    this.elements = elems;
    console.log(this.elements)
  }

  loadElements() {
    this.simpleLoader();
    this.getAllElements();
    this.dismissLoader();
  }

  async reserve(id: string) {
    this.alertController.create({
      message: 'Are you sure you want to reserve this?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Yes',
          handler: () => {
            this.apiService.reserveElement(id).subscribe(  {
              error: (error) => {
                const message = error.error?.error ?? 'Unknown server error';
                console.log(message);
              },
            })
            window.location.reload();
          }
        }
      ]
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
