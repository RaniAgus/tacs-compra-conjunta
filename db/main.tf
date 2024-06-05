terraform {
  required_providers {
    mongodbatlas = {
      source  = "mongodb/mongodbatlas"
      version = "~> 1.0"
    }
  }
}

provider "mongodbatlas" {
  public_key  = var.mongodbatlas_public_key
  private_key = var.mongodbatlas_private_key
}

data "mongodbatlas_roles_org_id" "project" {
}

resource "mongodbatlas_project" "project" {
  name   = var.mongodbatlas_project_name
  org_id = data.mongodbatlas_roles_org_id.project.org_id
}

resource "mongodbatlas_project_ip_access_list" "ip" {
  project_id = mongodbatlas_project.project.id
  cidr_block = var.mongodbatlas_allowed_cidr_block
  comment    = "CIDR Block for accessing the cluster"
}

resource "mongodbatlas_cluster" "cluster" {
  project_id                  = mongodbatlas_project.project.id
  name                        = var.mongodbatlas_cluster_name
  provider_name               = "TENANT"
  backing_provider_name       = "AWS"
  provider_region_name        = "US_EAST_1"
  provider_instance_size_name = "M0"
}

resource "mongodbatlas_database_user" "admin" {
  username           = var.mongodb_admin_username
  password           = var.mongodb_admin_password
  project_id         = mongodbatlas_project.project.id
  auth_database_name = var.mongodb_auth_database_name

  roles {
    role_name     = "atlasAdmin"
    database_name = var.mongodb_auth_database_name
  }

  scopes {
    name = var.mongodbatlas_cluster_name
    type = "CLUSTER"
  }
}

resource "mongodbatlas_database_user" "appuser" {
  username           = var.mongodb_app_username
  password           = var.mongodb_app_password
  project_id         = mongodbatlas_project.project.id
  auth_database_name = var.mongodb_auth_database_name

  roles {
    role_name     = "readWriteAnyDatabase"
    database_name = var.mongodb_auth_database_name
  }

  scopes {
    name = var.mongodbatlas_cluster_name
    type = "CLUSTER"
  }
}
