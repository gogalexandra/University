export class updateTaskRequest{
    name: string;
    info: string;
    duration: number;
    isDone: number;

    constructor( name: string, info: string, duration: number, isDone: number){
        this.name = name;
        this.info = info;
        this.duration = duration;
        this.isDone = isDone;
    }
}