<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="travelPlanProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="travelPlanProcessDetailsHeading">
          <span v-text="$t('travelPlanApp.travelPlanProcess.detail.title')">TravelPlanProcess</span> {{ travelPlanProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="travelPlanProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('travelPlanApp.travelPlanProcess.detail.title')">TravelPlanProcess</span> #{{ travelPlanProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="travelPlanProcess.travelPlan">
              <h5 class="card-header">
                <span class="title"> Travel Plan #{{ travelPlanProcess.travelPlan.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelPlanApp.travelPlanProcess.travelName')">travelName</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="travelName"
                    id="travel-plan-travelName"
                    data-cy="travelName"
                    v-model="travelPlanProcess.travelPlan.travelName"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelPlanApp.travelPlanProcess.startDate')">startDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="startDate"
                    id="travel-plan-startDate"
                    data-cy="startDate"
                    v-model="travelPlanProcess.travelPlan.startDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelPlanApp.travelPlanProcess.endDate')">endDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="endDate"
                    id="travel-plan-endDate"
                    data-cy="endDate"
                    v-model="travelPlanProcess.travelPlan.endDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelPlanApp.travelPlanProcess.airlineTicketNumber')"
                    >airlineTicketNumber</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="airlineTicketNumber"
                    id="travel-plan-airlineTicketNumber"
                    data-cy="airlineTicketNumber"
                    v-model="travelPlanProcess.travelPlan.airlineTicketNumber"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelPlanApp.travelPlanProcess.hotelBookingNumber')"
                    >hotelBookingNumber</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="hotelBookingNumber"
                    id="travel-plan-hotelBookingNumber"
                    data-cy="hotelBookingNumber"
                    v-model="travelPlanProcess.travelPlan.hotelBookingNumber"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelPlanApp.travelPlanProcess.carBookingNumber')">carBookingNumber</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="carBookingNumber"
                    id="travel-plan-carBookingNumber"
                    data-cy="carBookingNumber"
                    v-model="travelPlanProcess.travelPlan.carBookingNumber"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanApp.travelPlanProcess.airlineCompany')"
                    for="travel-plan-process-airlineCompany"
                    >Airline Company</label
                  >
                  <input
                    v-if="travelPlanProcess.travelPlan.airlineCompany"
                    readonly
                    type="text"
                    class="form-control"
                    name="airlineCompany"
                    id="travel-plan-airlineCompany"
                    data-cy="airlineCompany"
                    :value="travelPlanProcess.travelPlan.airlineCompany.name"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="airlineCompany"
                    id="travel-plan-airlineCompany"
                    data-cy="airlineCompany"
                    value=""
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('travelPlanApp.travelPlanProcess.hotel')" for="travel-plan-process-hotel"
                    >Hotel</label
                  >
                  <input
                    v-if="travelPlanProcess.travelPlan.hotel"
                    readonly
                    type="text"
                    class="form-control"
                    name="hotel"
                    id="travel-plan-hotel"
                    data-cy="hotel"
                    :value="travelPlanProcess.travelPlan.hotel.name"
                  />
                  <input v-else readonly type="text" class="form-control" name="hotel" id="travel-plan-hotel" data-cy="hotel" value="" />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanApp.travelPlanProcess.rentalCarCompany')"
                    for="travel-plan-process-rentalCarCompany"
                    >Rental Car Company</label
                  >
                  <input
                    v-if="travelPlanProcess.travelPlan.rentalCarCompany"
                    readonly
                    type="text"
                    class="form-control"
                    name="rentalCarCompany"
                    id="travel-plan-rentalCarCompany"
                    data-cy="rentalCarCompany"
                    :value="travelPlanProcess.travelPlan.rentalCarCompany.name"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="rentalCarCompany"
                    id="travel-plan-rentalCarCompany"
                    data-cy="rentalCarCompany"
                    value=""
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./travel-plan-process-details.component.ts"></script>
