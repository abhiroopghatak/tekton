package com.abhiroop.kubetime.cluster.restclient.http.constants;

public interface ClusterConstants {

	interface RestApiRelativePath {

		public static final String CLUSTER_VERSIONS = "api/config.openshift.io/v1/clusterversions";
	}

	interface ResourceStatus {

		public static final String ACTIVE = "Active";
		public static final String TERMINATING = "Terminating";
	}

	enum MemoryUnit {

		Ki, Mi, Gi, Ti, Pi, Ei;

	}
}
