/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AirlineCompanyComponent from '@/entities/airline-company/airline-company.vue';
import AirlineCompanyClass from '@/entities/airline-company/airline-company.component';
import AirlineCompanyService from '@/entities/airline-company/airline-company.service';

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
  describe('AirlineCompany Management Component', () => {
    let wrapper: Wrapper<AirlineCompanyClass>;
    let comp: AirlineCompanyClass;
    let airlineCompanyServiceStub: SinonStubbedInstance<AirlineCompanyService>;

    beforeEach(() => {
      airlineCompanyServiceStub = sinon.createStubInstance<AirlineCompanyService>(AirlineCompanyService);
      airlineCompanyServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AirlineCompanyClass>(AirlineCompanyComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          airlineCompanyService: () => airlineCompanyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      airlineCompanyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAirlineCompanys();
      await comp.$nextTick();

      // THEN
      expect(airlineCompanyServiceStub.retrieve.called).toBeTruthy();
      expect(comp.airlineCompanies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      airlineCompanyServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeAirlineCompany();
      await comp.$nextTick();

      // THEN
      expect(airlineCompanyServiceStub.delete.called).toBeTruthy();
      expect(airlineCompanyServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
