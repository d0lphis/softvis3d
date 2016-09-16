import {Shape, Vector2} from "three";
import {Dimension} from "./Dimension";
import {Position} from "./Position";

export class SoftVis3dShape extends Shape {

    public key: string;

    public position: Position;
    public dimensions: Dimension;
    public color: number;
    public opacity: number;

    constructor(points: Vector2[], key: string) {
        super(points);

        this.key = key;
        this.position = {
            x: 0,
            y: 0,
            z: 0
        };
        this.dimensions = {
            length: 0,
            width: 0,
            height: 0
        };
    }
}