import { IAirlineCompany } from '@/shared/model/airline-company.model';
import { IHotel } from '@/shared/model/hotel.model';
import { IRentalCarCompany } from '@/shared/model/rental-car-company.model';

export interface ITravelPlan {
  id?: number;
  travelName?: string | null;
  userName?: string | null;
  userEmail?: string | null;
  startDate?: Date | null;
  endDate?: Date | null;
  airlineTicketNumber?: string | null;
  hotelBookingNumber?: string | null;
  carBookingNumber?: string | null;
  airlineCompany?: IAirlineCompany | null;
  hotel?: IHotel | null;
  rentalCarCompany?: IRentalCarCompany | null;
}

export class TravelPlan implements ITravelPlan {
  constructor(
    public id?: number,
    public travelName?: string | null,
    public userName?: string | null,
    public userEmail?: string | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public airlineTicketNumber?: string | null,
    public hotelBookingNumber?: string | null,
    public carBookingNumber?: string | null,
    public airlineCompany?: IAirlineCompany | null,
    public hotel?: IHotel | null,
    public rentalCarCompany?: IRentalCarCompany | null
  ) {}
}
