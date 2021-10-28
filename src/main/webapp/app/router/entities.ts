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
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
