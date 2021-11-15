<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="travelPlan">
        <h2 class="jh-entity-heading" data-cy="travelPlanDetailsHeading">
          <span v-text="$t('travelPlanApp.travelPlan.detail.title')">TravelPlan</span> {{ travelPlan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.travelName')">Travel Name</span>
          </dt>
          <dd>
            <span>{{ travelPlan.travelName }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.userName')">User Name</span>
          </dt>
          <dd>
            <span>{{ travelPlan.userName }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.userEmail')">User Email</span>
          </dt>
          <dd>
            <span>{{ travelPlan.userEmail }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.startDate')">Start Date</span>
          </dt>
          <dd>
            <span>{{ travelPlan.startDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.endDate')">End Date</span>
          </dt>
          <dd>
            <span>{{ travelPlan.endDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.airlineTicketNumber')">Airline Ticket Number</span>
          </dt>
          <dd>
            <span>{{ travelPlan.airlineTicketNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.hotelBookingNumber')">Hotel Booking Number</span>
          </dt>
          <dd>
            <span>{{ travelPlan.hotelBookingNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.carBookingNumber')">Car Booking Number</span>
          </dt>
          <dd>
            <span>{{ travelPlan.carBookingNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.airlineCompany')">Airline Company</span>
          </dt>
          <dd>
            <div v-if="travelPlan.airlineCompany">
              <router-link :to="{ name: 'AirlineCompanyView', params: { airlineCompanyId: travelPlan.airlineCompany.id } }">{{
                travelPlan.airlineCompany.name
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.hotel')">Hotel</span>
          </dt>
          <dd>
            <div v-if="travelPlan.hotel">
              <router-link :to="{ name: 'HotelView', params: { hotelId: travelPlan.hotel.id } }">{{ travelPlan.hotel.name }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.travelPlan.rentalCarCompany')">Rental Car Company</span>
          </dt>
          <dd>
            <div v-if="travelPlan.rentalCarCompany">
              <router-link :to="{ name: 'RentalCarCompanyView', params: { rentalCarCompanyId: travelPlan.rentalCarCompany.id } }">{{
                travelPlan.rentalCarCompany.name
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./travel-plan-details.component.ts"></script>
