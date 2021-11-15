<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelPlanApp.rentalCarCompany.home.createOrEditLabel"
          data-cy="RentalCarCompanyCreateUpdateHeading"
          v-text="$t('travelPlanApp.rentalCarCompany.home.createOrEditLabel')"
        >
          Create or edit a RentalCarCompany
        </h2>
        <div>
          <div class="form-group" v-if="rentalCarCompany.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="rentalCarCompany.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelPlanApp.rentalCarCompany.name')" for="rental-car-company-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="rental-car-company-name"
              data-cy="name"
              :class="{ valid: !$v.rentalCarCompany.name.$invalid, invalid: $v.rentalCarCompany.name.$invalid }"
              v-model="$v.rentalCarCompany.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelPlanApp.rentalCarCompany.website')" for="rental-car-company-website"
              >Website</label
            >
            <input
              type="text"
              class="form-control"
              name="website"
              id="rental-car-company-website"
              data-cy="website"
              :class="{ valid: !$v.rentalCarCompany.website.$invalid, invalid: $v.rentalCarCompany.website.$invalid }"
              v-model="$v.rentalCarCompany.website.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelPlanApp.rentalCarCompany.email')" for="rental-car-company-email"
              >Email</label
            >
            <input
              type="text"
              class="form-control"
              name="email"
              id="rental-car-company-email"
              data-cy="email"
              :class="{ valid: !$v.rentalCarCompany.email.$invalid, invalid: $v.rentalCarCompany.email.$invalid }"
              v-model="$v.rentalCarCompany.email.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.rentalCarCompany.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./rental-car-company-update.component.ts"></script>
