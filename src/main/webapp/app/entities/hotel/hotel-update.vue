<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelPlanApp.hotel.home.createOrEditLabel"
          data-cy="HotelCreateUpdateHeading"
          v-text="$t('travelPlanApp.hotel.home.createOrEditLabel')"
        >
          Create or edit a Hotel
        </h2>
        <div>
          <div class="form-group" v-if="hotel.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="hotel.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelPlanApp.hotel.name')" for="hotel-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="hotel-name"
              data-cy="name"
              :class="{ valid: !$v.hotel.name.$invalid, invalid: $v.hotel.name.$invalid }"
              v-model="$v.hotel.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelPlanApp.hotel.website')" for="hotel-website">Website</label>
            <input
              type="text"
              class="form-control"
              name="website"
              id="hotel-website"
              data-cy="website"
              :class="{ valid: !$v.hotel.website.$invalid, invalid: $v.hotel.website.$invalid }"
              v-model="$v.hotel.website.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('travelPlanApp.hotel.email')" for="hotel-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="hotel-email"
              data-cy="email"
              :class="{ valid: !$v.hotel.email.$invalid, invalid: $v.hotel.email.$invalid }"
              v-model="$v.hotel.email.$model"
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
            :disabled="$v.hotel.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./hotel-update.component.ts"></script>
