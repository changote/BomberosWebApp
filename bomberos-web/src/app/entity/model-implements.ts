import { IUser } from "./model-interface";

export class User implements IUser {
  id: number | null = null;
  username: string = '';
  password: string = '';
}

export class Registro {
  fecha: Date = new Date();
  user: string = '';
  idUnidad: any = undefined;
  lateral: {
    parteList: { idParte: number; isOk?: boolean }[]; // Cambio aquí
    isLateral: boolean;
    observacion: string;
  } = {
    parteList: [],
    isLateral: true,
    observacion: '',
  };
  motor: {
    parteList: { idParte: number; isOk?: boolean }[]; // Cambio aquí
    isLateral: boolean;
    observacion: string;
  } = {
    parteList: [],
    isLateral: false,
    observacion: '',
  };

  constructor(data?: any) {
    if (data) {
      this.fecha = new Date();
      this.user = data.user || '';
      this.idUnidad = data.idUnidad || 0;

      if (data.lateral) {
        this.lateral = {
          parteList: data.lateral.parteList.map((parte: any) => ({
            idParte: parte.idParte || 0,
            isOk: undefined, // Cambio aquí
          })),
          isLateral: true,
          observacion: data.lateral.observacion || '',
        };
      }

      if (data.motor) {
        this.motor = {
          parteList: data.motor.parteList.map((parte: any) => ({
            idParte: parte.idParte || 0,
            isOk: undefined, // Cambio aquí
          })),
          isLateral: false,
          observacion: data.motor.observacion || '',
        };
      }
    }
  }
}





