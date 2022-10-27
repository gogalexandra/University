import {Driver} from "./driver-model";

export interface Team {
  id: number;
  name: string;
  drivers: Driver[];
  //participations: Array<Participation>
}
