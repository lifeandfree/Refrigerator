package ru.innopolis.refrigerator.core.model.enumcls;

public class ComplexityMethod {

	public static void main(String[] args) {
		System.out.println(getOptionsForSelect());
	}
	public static String getOptionsForSelect(){
		StringBuilder options = new StringBuilder();

		for (Complexity complexity: Complexity.values()
			 ) {
			options.append("<option>" + complexity + "</option>");

		}


		return options.toString();
	}
}
