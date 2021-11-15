/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import RentalCarCompanyUpdateComponent from '@/entities/rental-car-company/rental-car-company-update.vue';
import RentalCarCompanyClass from '@/entities/rental-car-company/rental-car-company-update.component';
import RentalCarCompanyService from '@/entities/rental-car-company/rental-car-company.service';

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
  describe('RentalCarCompany Management Update Component', () => {
    let wrapper: Wrapper<RentalCarCompanyClass>;
    let comp: RentalCarCompanyClass;
    let rentalCarCompanyServiceStub: SinonStubbedInstance<RentalCarCompanyService>;

    beforeEach(() => {
      rentalCarCompanyServiceStub = sinon.createStubInstance<RentalCarCompanyService>(RentalCarCompanyService);

      wrapper = shallowMount<RentalCarCompanyClass>(RentalCarCompanyUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          rentalCarCompanyService: () => rentalCarCompanyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.rentalCarCompany = entity;
        rentalCarCompanyServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rentalCarCompanyServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.rentalCarCompany = entity;
        rentalCarCompanyServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rentalCarCompanyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRentalCarCompany = { id: 123 };
        rentalCarCompanyServiceStub.find.resolves(foundRentalCarCompany);
        rentalCarCompanyServiceStub.retrieve.resolves([foundRentalCarCompany]);

        // WHEN
        comp.beforeRouteEnter({ params: { rentalCarCompanyId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.rentalCarCompany).toBe(foundRentalCarCompany);
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
