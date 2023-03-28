import { Component } from '@angular/core';
import {WebsocketService} from "../websocket.service";
import {AlertController} from "@ionic/angular";

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  providers: [WebsocketService]
})
export class HomePage {

  constructor(private alertController: AlertController, private websocketService: WebsocketService) {
    this.websocketService.messages.subscribe(response => {
      const res = JSON.stringify(response);
      const entry = JSON.parse(res);
      console.log(Object.keys(entry));
      this.showAlert('Name ' + entry.number);
    });
  }

  showAlert(message: string) {
    this.alertController.create({
      header: 'Info',
      subHeader: 'New entry added',
      message: message,
      buttons: ['OK']
    }).then(res => {
      res.present();
    });
  }
}
