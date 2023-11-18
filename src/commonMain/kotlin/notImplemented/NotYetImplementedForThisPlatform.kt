package notImplemented

import configuration.Configuration

object NotYetImplementedForThisPlatform : NotImplementedErrorProvider(
	"This has not yet been implemented for the platform '${Configuration.platform}', sorry."
)
