package notImplemented

import configuration.Configuration

object NotApplicableForThisPlatform : NotImplementedErrorProvider(
	"This will not be implemented for the platform '${Configuration.platform}'."
)
