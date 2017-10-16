package ru.innopolis.refrigerator.xml.serialization.refrigerator;

import ru.innopolis.refrigerator.core.model.refrigerator.Refrigerator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Refrigerators", propOrder = { "refrigerators"
})
@XmlRootElement(name = "Refrigerators")
public class Refrigerators {

	public Refrigerators() {
		this.refrigerators = new ArrayList<>();
	}

	public Refrigerators(List<Refrigerator> refrigerators) {
		this.refrigerators = refrigerators;
	}

	@XmlElement(required = true)
	private List<Refrigerator> refrigerators;

	public List<Refrigerator> getRefrigerators() {
		return refrigerators;
	}

	public void setRefrigerators(List<Refrigerator> refrigerators) {
		this.refrigerators = refrigerators;
	}

	@Override
	public String toString() {
		return "Refrigerators{" + "refrigerators=" + this.refrigerators + '}';
	}
}
