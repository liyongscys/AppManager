package com.cabletech.rms.proxy.web.edit;

/**
 * @author YuLeyuan
 *
 */
public class ComboEditor {
    private String type="combobox";
    private Options options;    
    /**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


    /**
	 * @return the options
	 */
	public Options getOptions() {
		return options;
	}


	/**
	 * @param options the options to set
	 */
	public void setOptions(Options options) {
		this.options = options;
	}


	public ComboEditor(String url){
		setOptions(new Options(url));
    }

	class Options{
    	private String valueField="value";
    	private String textField="display";
    	private String url;
    	private boolean required=true;
    	
    	public Options(String url){
    		this.url=url;
    	}
    	
    	public Options(String url, boolean required){
    		this.url=url;
    		this.required=required;
    	}
    	
		/**
		 * @return the valueField
		 */
		public String getValueField() {
			return valueField;
		}
		/**
		 * @param valueField the valueField to set
		 */
		public void setValueField(String valueField) {
			this.valueField = valueField;
		}
		/**
		 * @return the textField
		 */
		public String getTextField() {
			return textField;
		}
		/**
		 * @param textField the textField to set
		 */
		public void setTextField(String textField) {
			this.textField = textField;
		}
		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}
		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/**
		 * @return the required
		 */
		public boolean isRequired() {
			return required;
		}
		/**
		 * @param required the required to set
		 */
		public void setRequired(boolean required) {
			this.required = required;
		} 
    	
    }
    
}
