/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import AirlineCompanyDetailComponent from '@/entities/airline-company/airline-company-details.vue';
import AirlineCompanyClass from '@/entities/airline-company/airline-company-details.component';
import AirlineCompanyService from '@/entities/airline-company/airline-company.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('AirlineCompany Management Detail Component', () => {
    let wrapper: Wrapper<AirlineCompanyClass>;
    let comp: AirlineCompanyClass;
    let airlineCompanyServiceStub: SinonStubbedInstance<AirlineCompanyService>;

    beforeEach(() => {
      airlineCompanyServiceStub = sinon.createStubInstance<AirlineCompanyService>(AirlineCompanyService);

      wrapper = shallowMount<AirlineCompanyClass>(AirlineCompanyDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { airlineCompanyService: () => airlineCompanyServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAirlineCompany = { id: 123 };
        airlineCompanyServiceStub.find.resolves(foundAirlineCompany);

        // WHEN
        comp.retrieveAirlineCompany(123);
        await comp.$nextTick();

        // THEN
        expect(comp.airlineCompany).toBe(foundAirlineCompany);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAirlineCompany = { id: 123 };
        airlineCompanyServiceStub.find.resolves(foundAirlineCompany);

        // WHEN
        comp.beforeRouteEnter({ params: { airlineCompanyId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.airlineCompany).toBe(foundAirlineCompany);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
