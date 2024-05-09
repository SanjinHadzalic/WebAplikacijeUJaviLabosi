import { Vozilo } from "./vozilo";

export interface Review {
    id: number;
    title: string;
    text: string;
    grade: number;
    vozilo: Vozilo;
}