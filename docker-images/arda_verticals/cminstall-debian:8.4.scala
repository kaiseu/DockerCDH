  val nodePrefix = "bw-node"
  val nodeSize = 100
  val numberFixedLength = 1

  val nodes = defineNodes(nodePrefix, 1, nodeSize, numberFixedLength)
  
  val dockerHub = "10.239.47.144:5000"
  val image = s"$dockerHub/debian:8.4_cdh"
  
  val verticalName = "ubuntu"
  val firstHost = nodes(9)
  //val depolyCode = com.intel.bdt.analytics.arda.utils.DataGenerator.id
  val microServices = range(1, 4).map(index => {
    val name = s"$verticalName-$index"
    defineMicroService withName(name) useImage(image) atHost(firstHost) withBridge(true) //withVolumes(volumes)
  })
  val service = defineService withName(s"$verticalName-service") addMicroServices(microServices)
  val vertical = defineVertical withName(verticalName) addService(service)
  vertical
