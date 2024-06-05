terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "~> 2.0"
    }
  }
}

provider "digitalocean" {
  token = var.do_token
}

resource "digitalocean_app" "app" {
  spec {
    name   = var.do_app_name
    region = "nyc"

    env {
      key   = "JWT_SECRET_KEY"
      value = var.jwt_secret_key
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "MONGO_URI"
      value = var.database_uri
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_ACCESS_KEY"
      value = var.s3_access_key
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_SECRET_KEY"
      value = var.s3_secret_key
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_ENDPOINT"
      value = var.s3_endpoint
      scope = "RUN_TIME"
      type  = "SECRET"
    }

    env {
      key   = "S3_PUBLIC_ENDPOINT"
      value = var.s3_public_endpoint
      scope = "RUN_TIME"
      type  = "GENERAL"
    }

    env {
      key   = "S3_BUCKET_NAME"
      value = var.s3_bucket_name
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

      health_check {
        http_path = "/health"
      }

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
