<template>
  <div>
    <h2 id="page-heading" data-cy="RentalCarCompanyHeading">
      <span v-text="$t('travelPlanApp.rentalCarCompany.home.title')" id="rental-car-company-heading">Rental Car Companies</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('travelPlanApp.rentalCarCompany.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'RentalCarCompanyCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-rental-car-company"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('travelPlanApp.rentalCarCompany.home.createLabel')"> Create a new Rental Car Company </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && rentalCarCompanies && rentalCarCompanies.length === 0">
      <span v-text="$t('travelPlanApp.rentalCarCompany.home.notFound')">No rentalCarCompanies found</span>
    </div>
    <div class="table-responsive" v-if="rentalCarCompanies && rentalCarCompanies.length > 0">
      <table class="table table-striped" aria-describedby="rentalCarCompanies">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('travelPlanApp.rentalCarCompany.name')">Name</span></th>
            <th scope="row"><span v-text="$t('travelPlanApp.rentalCarCompany.website')">Website</span></th>
            <th scope="row"><span v-text="$t('travelPlanApp.rentalCarCompany.email')">Email</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rentalCarCompany in rentalCarCompanies" :key="rentalCarCompany.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RentalCarCompanyView', params: { rentalCarCompanyId: rentalCarCompany.id } }">{{
                rentalCarCompany.id
              }}</router-link>
            </td>
            <td>{{ rentalCarCompany.name }}</td>
            <td>{{ rentalCarCompany.website }}</td>
            <td>{{ rentalCarCompany.email }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RentalCarCompanyView', params: { rentalCarCompanyId: rentalCarCompany.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RentalCarCompanyEdit', params: { rentalCarCompanyId: rentalCarCompany.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(rentalCarCompany)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="travelPlanApp.rentalCarCompany.delete.question"
          data-cy="rentalCarCompanyDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-rentalCarCompany-heading" v-text="$t('travelPlanApp.rentalCarCompany.delete.question', { id: removeId })">
          Are you sure you want to delete this Rental Car Company?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-rentalCarCompany"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeRentalCarCompany()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./rental-car-company.component.ts"></script>
