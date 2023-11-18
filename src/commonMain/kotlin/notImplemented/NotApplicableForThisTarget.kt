package notImplemented

import configuration.Configuration

object NotApplicableForThisTarget : NotImplementedErrorProvider(
	"This will not be implemented for the target '${Configuration.target}'."
)
