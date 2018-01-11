val nodePrefix = "bw-node"
  val nodeSize = 100
  val numberFixedLength = 1

  val nodes = defineNodes(nodePrefix, 1, nodeSize, numberFixedLength)
  
  val dockerHub = "10.239.47.144:5000"
  val debianImage = s"$dockerHub/debian:8.4_cdh"
  
  val debianVerticalName = "debian"
  val firstHost = nodes(8)
  val depolyCode = com.intel.bdt.analytics.arda.utils.DataGenerator.id
  val debianMicroServices = range(1, 4).map(index => {
    val name = s"$debianVerticalName-$index"
    defineMicroService withName(name) useImage(debianImage) atHost(firstHost) withBridge(true)
  })
  val debianService = defineService withName(s"$debianVerticalName-service") addMicroServices(debianMicroServices)
  val debianVertical = defineVertical withName(debianVerticalName) addService(debianService)
  debianVertical
