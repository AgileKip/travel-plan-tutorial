/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import HotelDetailComponent from '@/entities/hotel/hotel-details.vue';
import HotelClass from '@/entities/hotel/hotel-details.component';
import HotelService from '@/entities/hotel/hotel.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Hotel Management Detail Component', () => {
    let wrapper: Wrapper<HotelClass>;
    let comp: HotelClass;
    let hotelServiceStub: SinonStubbedInstance<HotelService>;

    beforeEach(() => {
      hotelServiceStub = sinon.createStubInstance<HotelService>(HotelService);

      wrapper = shallowMount<HotelClass>(HotelDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { hotelService: () => hotelServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundHotel = { id: 123 };
        hotelServiceStub.find.resolves(foundHotel);

        // WHEN
        comp.retrieveHotel(123);
        await comp.$nextTick();

        // THEN
        expect(comp.hotel).toBe(foundHotel);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundHotel = { id: 123 };
        hotelServiceStub.find.resolves(foundHotel);

        // WHEN
        comp.beforeRouteEnter({ params: { hotelId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.hotel).toBe(foundHotel);
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
