/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import AirlineCompanyUpdateComponent from '@/entities/airline-company/airline-company-update.vue';
import AirlineCompanyClass from '@/entities/airline-company/airline-company-update.component';
import AirlineCompanyService from '@/entities/airline-company/airline-company.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('AirlineCompany Management Update Component', () => {
    let wrapper: Wrapper<AirlineCompanyClass>;
    let comp: AirlineCompanyClass;
    let airlineCompanyServiceStub: SinonStubbedInstance<AirlineCompanyService>;

    beforeEach(() => {
      airlineCompanyServiceStub = sinon.createStubInstance<AirlineCompanyService>(AirlineCompanyService);

      wrapper = shallowMount<AirlineCompanyClass>(AirlineCompanyUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          airlineCompanyService: () => airlineCompanyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.airlineCompany = entity;
        airlineCompanyServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(airlineCompanyServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.airlineCompany = entity;
        airlineCompanyServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(airlineCompanyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAirlineCompany = { id: 123 };
        airlineCompanyServiceStub.find.resolves(foundAirlineCompany);
        airlineCompanyServiceStub.retrieve.resolves([foundAirlineCompany]);

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
