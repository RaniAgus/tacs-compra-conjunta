variable "do_token" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "do_registry" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "do_repository" {
  type    = string
  default = "tacs_compra_conjunta"
}

variable "do_backend_tag" {
  type      = string
  default = "backend"
}

variable "jwt_secret_key" {
  type      = string
  nullable  = false
  sensitive = true
}

provider "digitalocean" {
  token = var.do_token
}

resource "digitalocean_app" "app" {
  spec {
    name   = "tacs-compra-conjunta"
    region = "nyc"

    env {
      key   = "JWT_SECRET_KEY"
      value = var.jwt_secret_key
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "MONGO_URI"
      value = "mongodb+srv://${
        var.mongodb_app_username
      }:${
        var.mongodb_app_password
      }@${
        trimprefix(mongodbatlas_cluster.cluster.connection_strings[0].standard_srv, "mongodb+srv://")
      }/${
        var.mongodbatlas_cluster_name
      }?retryWrites=true&w=majority&appName=${
        var.mongodbatlas_cluster_name
      }"
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_ACCESS_KEY"
      value = var.cloudflare_access_key
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_SECRET_KEY"
      value = var.cloudflare_secret_key
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_ENDPOINT"
      value = "https://${var.cloudflare_account_id}.r2.cloudflarestorage.com"
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_PUBLIC_ENDPOINT"
      value = var.cloudflare_public_endpoint
      scope = "RUN_TIME"
      type  = "GENERAL"
    }

    env {
      key   = "S3_BUCKET_NAME"
      value = var.cloudflare_bucket_name
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_REGION"
      value = "us-east-1"
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    service {
      name               = "backend"
      instance_count     = 1
      instance_size_slug = "basic-xxs"
      http_port          = 8080

      image {
        registry_type = "DOCR"
        registry      = var.do_registry
        repository    = var.do_repository
        tag           = var.do_backend_tag

        deploy_on_push {
          enabled = true
        }
      }
    }
  }
}
