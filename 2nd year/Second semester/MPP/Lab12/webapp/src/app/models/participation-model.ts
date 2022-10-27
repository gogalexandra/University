import {Team} from "./team-model";
import {Race} from "./race-model";

export interface Participation {
  id: number;
  points: number
  team: Team;
  race: Race;
}
