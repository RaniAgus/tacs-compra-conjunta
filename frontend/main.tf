terraform {
  required_providers {
    vercel = {
      source = "vercel/vercel"
      version = "~> 0.3"
    }
  }
}

provider "vercel" {
  api_token = var.vercel_api_token
  team      = var.vercel_org_id
}

resource "vercel_project" "frontend" {
  name           = var.vercel_project_name
  framework      = "nextjs"
  root_directory = "frontend"
  git_repository = {
    type              = "github"
    repo              = var.vercel_project_repo
    production_branch = var.vercel_project_branch
  }
  environment    = [ {
    key    = "BACKEND_URL"
    value  = var.backend_url
    target = ["production"]
  } ]
  ignore_command = "git diff HEAD^ HEAD --quiet -- ."
}

resource "vercel_deployment" "frontend" {
  project_id = vercel_project.frontend.id
  ref        = var.vercel_project_branch
}
