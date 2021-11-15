/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import HotelUpdateComponent from '@/entities/hotel/hotel-update.vue';
import HotelClass from '@/entities/hotel/hotel-update.component';
import HotelService from '@/entities/hotel/hotel.service';

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
  describe('Hotel Management Update Component', () => {
    let wrapper: Wrapper<HotelClass>;
    let comp: HotelClass;
    let hotelServiceStub: SinonStubbedInstance<HotelService>;

    beforeEach(() => {
      hotelServiceStub = sinon.createStubInstance<HotelService>(HotelService);

      wrapper = shallowMount<HotelClass>(HotelUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          hotelService: () => hotelServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.hotel = entity;
        hotelServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(hotelServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.hotel = entity;
        hotelServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(hotelServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundHotel = { id: 123 };
        hotelServiceStub.find.resolves(foundHotel);
        hotelServiceStub.retrieve.resolves([foundHotel]);

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
