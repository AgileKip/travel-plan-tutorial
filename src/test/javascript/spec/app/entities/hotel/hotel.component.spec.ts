/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import HotelComponent from '@/entities/hotel/hotel.vue';
import HotelClass from '@/entities/hotel/hotel.component';
import HotelService from '@/entities/hotel/hotel.service';

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
  describe('Hotel Management Component', () => {
    let wrapper: Wrapper<HotelClass>;
    let comp: HotelClass;
    let hotelServiceStub: SinonStubbedInstance<HotelService>;

    beforeEach(() => {
      hotelServiceStub = sinon.createStubInstance<HotelService>(HotelService);
      hotelServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<HotelClass>(HotelComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          hotelService: () => hotelServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      hotelServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllHotels();
      await comp.$nextTick();

      // THEN
      expect(hotelServiceStub.retrieve.called).toBeTruthy();
      expect(comp.hotels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      hotelServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeHotel();
      await comp.$nextTick();

      // THEN
      expect(hotelServiceStub.delete.called).toBeTruthy();
      expect(hotelServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
