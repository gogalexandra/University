import { Component, OnInit } from '@angular/core';
import {AlertController, ModalController} from "@ionic/angular";
import {ApiService} from "../../api.service";
import {DatabaseService} from "../../database.service";

@Component({
  selector: 'app-add-space',
  templateUrl: './add-space.page.html',
  styleUrls: ['./add-space.page.scss'],
})
export class AddSpacePage implements OnInit {
  element: any | undefined;
  number: string | undefined;
  address: string | undefined;
  status: string | undefined;
  count: string | undefined;

  constructor(public modalCtrl: ModalController, public apiService: ApiService, private alertController: AlertController,
              private db: DatabaseService) {}

  ngOnInit() {
  }

  async addElement() {
    if (!this.number || !this.address || !this.status || !this.count) {
      this.showAlert('Please complete all missing fields!')
    } else {
      this.element = ({ number: this.number, address: this.address,
        status: this.status, count: parseInt(this.count) })

      this.apiService.addElement(this.element).subscribe(  {
        complete: () => {
          this.showAlert('New element inserted successfully!')
        },
        error: (error) => {
          // TODO: add checking for other type of alerts, check error.text or smth
          this.showAlert('The element was inserted successfully in the local database!');
          this.db.addElement(this.element);
        },
      })
      await this.dismiss();
    }
  }

  async dismiss() {
    await this.modalCtrl.dismiss(this.element);
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
}
