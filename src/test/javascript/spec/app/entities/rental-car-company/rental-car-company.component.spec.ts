/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import RentalCarCompanyComponent from '@/entities/rental-car-company/rental-car-company.vue';
import RentalCarCompanyClass from '@/entities/rental-car-company/rental-car-company.component';
import RentalCarCompanyService from '@/entities/rental-car-company/rental-car-company.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('RentalCarCompany Management Component', () => {
    let wrapper: Wrapper<RentalCarCompanyClass>;
    let comp: RentalCarCompanyClass;
    let rentalCarCompanyServiceStub: SinonStubbedInstance<RentalCarCompanyService>;

    beforeEach(() => {
      rentalCarCompanyServiceStub = sinon.createStubInstance<RentalCarCompanyService>(RentalCarCompanyService);
      rentalCarCompanyServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RentalCarCompanyClass>(RentalCarCompanyComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          rentalCarCompanyService: () => rentalCarCompanyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      rentalCarCompanyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRentalCarCompanys();
      await comp.$nextTick();

      // THEN
      expect(rentalCarCompanyServiceStub.retrieve.called).toBeTruthy();
      expect(comp.rentalCarCompanies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      rentalCarCompanyServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeRentalCarCompany();
      await comp.$nextTick();

      // THEN
      expect(rentalCarCompanyServiceStub.delete.called).toBeTruthy();
      expect(rentalCarCompanyServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
