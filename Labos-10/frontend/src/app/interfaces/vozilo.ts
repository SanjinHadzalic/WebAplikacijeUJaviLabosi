export interface Vozilo {
    id: number;
    registration: string;
    vin: string;
    maxNumberOfPassenger: number;
    shifter: string;
    airConditioning: string;
    numberOfDoors: number;
    fuelType: string;
    lastServiceDate: Date;
    nextServiceDate: Date;
    mileage: number;
}
