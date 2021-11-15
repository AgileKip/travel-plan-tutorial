/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RentalCarCompanyDetailComponent from '@/entities/rental-car-company/rental-car-company-details.vue';
import RentalCarCompanyClass from '@/entities/rental-car-company/rental-car-company-details.component';
import RentalCarCompanyService from '@/entities/rental-car-company/rental-car-company.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RentalCarCompany Management Detail Component', () => {
    let wrapper: Wrapper<RentalCarCompanyClass>;
    let comp: RentalCarCompanyClass;
    let rentalCarCompanyServiceStub: SinonStubbedInstance<RentalCarCompanyService>;

    beforeEach(() => {
      rentalCarCompanyServiceStub = sinon.createStubInstance<RentalCarCompanyService>(RentalCarCompanyService);

      wrapper = shallowMount<RentalCarCompanyClass>(RentalCarCompanyDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { rentalCarCompanyService: () => rentalCarCompanyServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRentalCarCompany = { id: 123 };
        rentalCarCompanyServiceStub.find.resolves(foundRentalCarCompany);

        // WHEN
        comp.retrieveRentalCarCompany(123);
        await comp.$nextTick();

        // THEN
        expect(comp.rentalCarCompany).toBe(foundRentalCarCompany);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRentalCarCompany = { id: 123 };
        rentalCarCompanyServiceStub.find.resolves(foundRentalCarCompany);

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
