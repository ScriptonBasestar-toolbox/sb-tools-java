package org.scriptonbasestar.tool.data.jpa.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * @author archmagece
 * @since 2015-06-06
 */
public class SsoNamingStrategy extends ImprovedNamingStrategy {

	private static final String PLURAL_SUFFIX = "s";

	/**
	 * Transforms class names to table names by using the described naming conventions.
	 *
	 * @param className
	 * @return The constructed table name.
	 */
	@Override
	public String classToTableName(String className) {
		String tableNameInSingularForm = super.classToTableName(className);
		return transformToPluralForm(tableNameInSingularForm);
	}

	private String transformToPluralForm(String tableNameInSingularForm) {
		StringBuilder pluralForm = new StringBuilder();

		pluralForm.append(tableNameInSingularForm);
		pluralForm.append(PLURAL_SUFFIX);

		return pluralForm.toString();
	}
}
