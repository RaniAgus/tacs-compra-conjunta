output "domain" {
  value = trimprefix(mongodbatlas_cluster.cluster.srv_address, "mongodb+srv://")
}

output "app_username" {
  value = mongodbatlas_database_user.appuser.username
}
