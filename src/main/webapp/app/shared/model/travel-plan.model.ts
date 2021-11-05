export interface ITravelPlan {
  id?: number;
  travelName?: string | null;
  userName?: string | null;
  userEmail?: string | null;
  startDate?: Date | null;
  endDate?: Date | null;
  airlineCompanyName?: string | null;
  airlineTicketNumber?: string | null;
  hotelName?: string | null;
  hotelBookingNumber?: string | null;
  carCompanyName?: string | null;
  carBookingNumber?: string | null;
}

export class TravelPlan implements ITravelPlan {
  constructor(
    public id?: number,
    public travelName?: string | null,
    public userName?: string | null,
    public userEmail?: string | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public airlineCompanyName?: string | null,
    public airlineTicketNumber?: string | null,
    public hotelName?: string | null,
    public hotelBookingNumber?: string | null,
    public carCompanyName?: string | null,
    public carBookingNumber?: string | null
  ) {}
}
