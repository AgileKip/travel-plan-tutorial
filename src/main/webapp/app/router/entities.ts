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
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
