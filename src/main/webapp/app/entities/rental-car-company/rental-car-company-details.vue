<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="rentalCarCompany">
        <h2 class="jh-entity-heading" data-cy="rentalCarCompanyDetailsHeading">
          <span v-text="$t('travelPlanApp.rentalCarCompany.detail.title')">RentalCarCompany</span> {{ rentalCarCompany.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('travelPlanApp.rentalCarCompany.name')">Name</span>
          </dt>
          <dd>
            <span>{{ rentalCarCompany.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.rentalCarCompany.website')">Website</span>
          </dt>
          <dd>
            <span>{{ rentalCarCompany.website }}</span>
          </dd>
          <dt>
            <span v-text="$t('travelPlanApp.rentalCarCompany.email')">Email</span>
          </dt>
          <dd>
            <span>{{ rentalCarCompany.email }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="rentalCarCompany.id"
          :to="{ name: 'RentalCarCompanyEdit', params: { rentalCarCompanyId: rentalCarCompany.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./rental-car-company-details.component.ts"></script>
