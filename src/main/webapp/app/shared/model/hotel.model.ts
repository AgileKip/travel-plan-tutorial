export interface IHotel {
  id?: number;
  name?: string | null;
  website?: string | null;
  email?: string | null;
}

export class Hotel implements IHotel {
  constructor(public id?: number, public name?: string | null, public website?: string | null, public email?: string | null) {}
}
