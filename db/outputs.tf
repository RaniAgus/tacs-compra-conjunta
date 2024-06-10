output "domain" {
  value = trimprefix(mongodbatlas_cluster.cluster.srv_address, "mongodb+srv://")
}
