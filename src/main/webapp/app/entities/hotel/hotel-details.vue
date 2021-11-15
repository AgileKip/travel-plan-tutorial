<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="hotel">
        <h2 class="jh-entity-heading" data-cy="hotelDetailsHeading">
          <span v-text="$t('travelPlanApp.hotel.detail.title')">Hotel</span> {{ hotel.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('travelPlanApp.hotel.name')">Name</span>
          </dt>
          <dd>
            <span>{{ hotel.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.hotel.website')">Website</span>
          </dt>
          <dd>
            <span>{{ hotel.website }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.hotel.email')">Email</span>
          </dt>
          <dd>
            <span>{{ hotel.email }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="hotel.id" :to="{ name: 'HotelEdit', params: { hotelId: hotel.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./hotel-details.component.ts"></script>
