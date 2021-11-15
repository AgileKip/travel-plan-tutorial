import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const TravelPlan = () => import('@/entities/travel-plan/travel-plan.vue');
// prettier-ignore
const TravelPlanDetails = () => import('@/entities/travel-plan/travel-plan-details.vue');
// prettier-ignore
const TravelPlanProcessDetails = () => import('@/entities/travel-plan-process/travel-plan-process-details.vue');
// prettier-ignore
const TravelPlanProcessList = () => import('@/entities/travel-plan-process/travel-plan-process-list.vue');
// prettier-ignore
const TravelPlanStartFormInit = () => import('@/entities/travel-plan-process/travel-plan-start-form-init.vue');
// prettier-ignore
const TravelPlanProcess_TaskFlightDetails = () => import('@/entities/travel-plan-process/task-flight/task-flight-details.vue');
// prettier-ignore
const TravelPlanProcess_TaskFlightExecute = () => import('@/entities/travel-plan-process/task-flight/task-flight-execute.vue');
// prettier-ignore
const TravelPlanProcess_TaskHotelDetails = () => import('@/entities/travel-plan-process/task-hotel/task-hotel-details.vue');
// prettier-ignore
const TravelPlanProcess_TaskHotelExecute = () => import('@/entities/travel-plan-process/task-hotel/task-hotel-execute.vue');
// prettier-ignore
const TravelPlanProcess_TaskCarDetails = () => import('@/entities/travel-plan-process/task-car/task-car-details.vue');
// prettier-ignore
// prettier-ignore
const TravelPlanProcess_TaskCarExecute = () => import('@/entities/travel-plan-process/task-car/task-car-execute.vue');
// prettier-ignore
const AirlineCompany = () => import('@/entities/airline-company/airline-company.vue');
// prettier-ignore
const AirlineCompanyUpdate = () => import('@/entities/airline-company/airline-company-update.vue');
// prettier-ignore
const AirlineCompanyDetails = () => import('@/entities/airline-company/airline-company-details.vue');
// prettier-ignore
const Hotel = () => import('@/entities/hotel/hotel.vue');
// prettier-ignore
const HotelUpdate = () => import('@/entities/hotel/hotel-update.vue');
// prettier-ignore
const HotelDetails = () => import('@/entities/hotel/hotel-details.vue');
// prettier-ignore
const RentalCarCompany = () => import('@/entities/rental-car-company/rental-car-company.vue');
// prettier-ignore
const RentalCarCompanyUpdate = () => import('@/entities/rental-car-company/rental-car-company-update.vue');
// prettier-ignore
const RentalCarCompanyDetails = () => import('@/entities/rental-car-company/rental-car-company-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/travel-plan',
    name: 'TravelPlan',
    component: TravelPlan,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/travel-plan/:travelPlanId/view',
    name: 'TravelPlanView',
    component: TravelPlanDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/instance/:processInstanceId/view',
    name: 'TravelPlanProcessView',
    component: TravelPlanProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/instances',
    name: 'TravelPlanProcessList',
    component: TravelPlanProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/init',
    name: 'TravelPlanStartFormInit',
    component: TravelPlanStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/task/TaskFlight/:taskInstanceId/view',
    name: 'TravelPlanProcess_TaskFlightDetails',
    component: TravelPlanProcess_TaskFlightDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/task/TaskFlight/:taskInstanceId/execute',
    name: 'TravelPlanProcess_TaskFlightExecute',
    component: TravelPlanProcess_TaskFlightExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/task/TaskHotel/:taskInstanceId/view',
    name: 'TravelPlanProcess_TaskHotelDetails',
    component: TravelPlanProcess_TaskHotelDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/task/TaskHotel/:taskInstanceId/execute',
    name: 'TravelPlanProcess_TaskHotelExecute',
    component: TravelPlanProcess_TaskHotelExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/task/TaskCar/:taskInstanceId/view',
    name: 'TravelPlanProcess_TaskCarDetails',
    component: TravelPlanProcess_TaskCarDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/process-definition/TravelPlanProcess/task/TaskCar/:taskInstanceId/execute',
    name: 'TravelPlanProcess_TaskCarExecute',
    component: TravelPlanProcess_TaskCarExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company',
    name: 'AirlineCompany',
    component: AirlineCompany,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company/new',
    name: 'AirlineCompanyCreate',
    component: AirlineCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company/:airlineCompanyId/edit',
    name: 'AirlineCompanyEdit',
    component: AirlineCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/airline-company/:airlineCompanyId/view',
    name: 'AirlineCompanyView',
    component: AirlineCompanyDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/hotel',
    name: 'Hotel',
    component: Hotel,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/hotel/new',
    name: 'HotelCreate',
    component: HotelUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/hotel/:hotelId/edit',
    name: 'HotelEdit',
    component: HotelUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/hotel/:hotelId/view',
    name: 'HotelView',
    component: HotelDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/rental-car-company',
    name: 'RentalCarCompany',
    component: RentalCarCompany,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/rental-car-company/new',
    name: 'RentalCarCompanyCreate',
    component: RentalCarCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/rental-car-company/:rentalCarCompanyId/edit',
    name: 'RentalCarCompanyEdit',
    component: RentalCarCompanyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/rental-car-company/:rentalCarCompanyId/view',
    name: 'RentalCarCompanyView',
    component: RentalCarCompanyDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
