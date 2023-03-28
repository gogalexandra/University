import { Component, OnInit } from '@angular/core';
import {AlertController, LoadingController, ModalController} from "@ionic/angular";
import {ApiService} from "../../api.service";
import {WebsocketService} from "../../websocket.service";

@Component({
  selector: 'app-main-stats',
  templateUrl: './main-stats.page.html',
  styleUrls: ['./main-stats.page.scss'],
  providers: [WebsocketService]
})
export class MainStatsPage implements OnInit {
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
      elems.sort((a, b) => (a.count > b.count) ? -1 : 1)
      elems.splice(5);

    })
    this.elements = elems;
    console.log(this.elements)
  }

  async updateElement(selectedElement: Object) {
    // const modal = await this.modalCtrl.create({
    //   component: UpdateCoursePage,
    //   componentProps: { item: selectedCourse }
    // })
    // modal.onDidDismiss().then(() => {
    //   this.getAllCourses();
    // })
    //
    // return await modal.present();
  }

  loadElements() {
    this.simpleLoader();
    this.getAllElements();
    this.dismissLoader();
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
