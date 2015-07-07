/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package example.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class FtthIF2 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FtthIF2\",\"namespace\":\"example.avro\",\"fields\":[{\"name\":\"event_time\",\"type\":\"string\"},{\"name\":\"neoss_code\",\"type\":\"string\"},{\"name\":\"ifname\",\"type\":\"string\"},{\"name\":\"onuid\",\"type\":\"string\"},{\"name\":\"ontmac\",\"type\":\"string\"},{\"name\":\"extrainfo\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence event_time;
  @Deprecated public java.lang.CharSequence neoss_code;
  @Deprecated public java.lang.CharSequence ifname;
  @Deprecated public java.lang.CharSequence onuid;
  @Deprecated public java.lang.CharSequence ontmac;
  @Deprecated public java.lang.CharSequence extrainfo;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public FtthIF2() {}

  /**
   * All-args constructor.
   */
  public FtthIF2(java.lang.CharSequence event_time, java.lang.CharSequence neoss_code, java.lang.CharSequence ifname, java.lang.CharSequence onuid, java.lang.CharSequence ontmac, java.lang.CharSequence extrainfo) {
    this.event_time = event_time;
    this.neoss_code = neoss_code;
    this.ifname = ifname;
    this.onuid = onuid;
    this.ontmac = ontmac;
    this.extrainfo = extrainfo;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return event_time;
    case 1: return neoss_code;
    case 2: return ifname;
    case 3: return onuid;
    case 4: return ontmac;
    case 5: return extrainfo;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: event_time = (java.lang.CharSequence)value$; break;
    case 1: neoss_code = (java.lang.CharSequence)value$; break;
    case 2: ifname = (java.lang.CharSequence)value$; break;
    case 3: onuid = (java.lang.CharSequence)value$; break;
    case 4: ontmac = (java.lang.CharSequence)value$; break;
    case 5: extrainfo = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'event_time' field.
   */
  public java.lang.CharSequence getEventTime() {
    return event_time;
  }

  /**
   * Sets the value of the 'event_time' field.
   * @param value the value to set.
   */
  public void setEventTime(java.lang.CharSequence value) {
    this.event_time = value;
  }

  /**
   * Gets the value of the 'neoss_code' field.
   */
  public java.lang.CharSequence getNeossCode() {
    return neoss_code;
  }

  /**
   * Sets the value of the 'neoss_code' field.
   * @param value the value to set.
   */
  public void setNeossCode(java.lang.CharSequence value) {
    this.neoss_code = value;
  }

  /**
   * Gets the value of the 'ifname' field.
   */
  public java.lang.CharSequence getIfname() {
    return ifname;
  }

  /**
   * Sets the value of the 'ifname' field.
   * @param value the value to set.
   */
  public void setIfname(java.lang.CharSequence value) {
    this.ifname = value;
  }

  /**
   * Gets the value of the 'onuid' field.
   */
  public java.lang.CharSequence getOnuid() {
    return onuid;
  }

  /**
   * Sets the value of the 'onuid' field.
   * @param value the value to set.
   */
  public void setOnuid(java.lang.CharSequence value) {
    this.onuid = value;
  }

  /**
   * Gets the value of the 'ontmac' field.
   */
  public java.lang.CharSequence getOntmac() {
    return ontmac;
  }

  /**
   * Sets the value of the 'ontmac' field.
   * @param value the value to set.
   */
  public void setOntmac(java.lang.CharSequence value) {
    this.ontmac = value;
  }

  /**
   * Gets the value of the 'extrainfo' field.
   */
  public java.lang.CharSequence getExtrainfo() {
    return extrainfo;
  }

  /**
   * Sets the value of the 'extrainfo' field.
   * @param value the value to set.
   */
  public void setExtrainfo(java.lang.CharSequence value) {
    this.extrainfo = value;
  }

  /** Creates a new FtthIF2 RecordBuilder */
  public static example.avro.FtthIF2.Builder newBuilder() {
    return new example.avro.FtthIF2.Builder();
  }
  
  /** Creates a new FtthIF2 RecordBuilder by copying an existing Builder */
  public static example.avro.FtthIF2.Builder newBuilder(example.avro.FtthIF2.Builder other) {
    return new example.avro.FtthIF2.Builder(other);
  }
  
  /** Creates a new FtthIF2 RecordBuilder by copying an existing FtthIF2 instance */
  public static example.avro.FtthIF2.Builder newBuilder(example.avro.FtthIF2 other) {
    return new example.avro.FtthIF2.Builder(other);
  }
  
  /**
   * RecordBuilder for FtthIF2 instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FtthIF2>
    implements org.apache.avro.data.RecordBuilder<FtthIF2> {

    private java.lang.CharSequence event_time;
    private java.lang.CharSequence neoss_code;
    private java.lang.CharSequence ifname;
    private java.lang.CharSequence onuid;
    private java.lang.CharSequence ontmac;
    private java.lang.CharSequence extrainfo;

    /** Creates a new Builder */
    private Builder() {
      super(example.avro.FtthIF2.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(example.avro.FtthIF2.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.event_time)) {
        this.event_time = data().deepCopy(fields()[0].schema(), other.event_time);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.neoss_code)) {
        this.neoss_code = data().deepCopy(fields()[1].schema(), other.neoss_code);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ifname)) {
        this.ifname = data().deepCopy(fields()[2].schema(), other.ifname);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.onuid)) {
        this.onuid = data().deepCopy(fields()[3].schema(), other.onuid);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.ontmac)) {
        this.ontmac = data().deepCopy(fields()[4].schema(), other.ontmac);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.extrainfo)) {
        this.extrainfo = data().deepCopy(fields()[5].schema(), other.extrainfo);
        fieldSetFlags()[5] = true;
      }
    }
    
    /** Creates a Builder by copying an existing FtthIF2 instance */
    private Builder(example.avro.FtthIF2 other) {
            super(example.avro.FtthIF2.SCHEMA$);
      if (isValidValue(fields()[0], other.event_time)) {
        this.event_time = data().deepCopy(fields()[0].schema(), other.event_time);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.neoss_code)) {
        this.neoss_code = data().deepCopy(fields()[1].schema(), other.neoss_code);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ifname)) {
        this.ifname = data().deepCopy(fields()[2].schema(), other.ifname);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.onuid)) {
        this.onuid = data().deepCopy(fields()[3].schema(), other.onuid);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.ontmac)) {
        this.ontmac = data().deepCopy(fields()[4].schema(), other.ontmac);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.extrainfo)) {
        this.extrainfo = data().deepCopy(fields()[5].schema(), other.extrainfo);
        fieldSetFlags()[5] = true;
      }
    }

    /** Gets the value of the 'event_time' field */
    public java.lang.CharSequence getEventTime() {
      return event_time;
    }
    
    /** Sets the value of the 'event_time' field */
    public example.avro.FtthIF2.Builder setEventTime(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.event_time = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'event_time' field has been set */
    public boolean hasEventTime() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'event_time' field */
    public example.avro.FtthIF2.Builder clearEventTime() {
      event_time = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'neoss_code' field */
    public java.lang.CharSequence getNeossCode() {
      return neoss_code;
    }
    
    /** Sets the value of the 'neoss_code' field */
    public example.avro.FtthIF2.Builder setNeossCode(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.neoss_code = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'neoss_code' field has been set */
    public boolean hasNeossCode() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'neoss_code' field */
    public example.avro.FtthIF2.Builder clearNeossCode() {
      neoss_code = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'ifname' field */
    public java.lang.CharSequence getIfname() {
      return ifname;
    }
    
    /** Sets the value of the 'ifname' field */
    public example.avro.FtthIF2.Builder setIfname(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.ifname = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'ifname' field has been set */
    public boolean hasIfname() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'ifname' field */
    public example.avro.FtthIF2.Builder clearIfname() {
      ifname = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'onuid' field */
    public java.lang.CharSequence getOnuid() {
      return onuid;
    }
    
    /** Sets the value of the 'onuid' field */
    public example.avro.FtthIF2.Builder setOnuid(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.onuid = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'onuid' field has been set */
    public boolean hasOnuid() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'onuid' field */
    public example.avro.FtthIF2.Builder clearOnuid() {
      onuid = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'ontmac' field */
    public java.lang.CharSequence getOntmac() {
      return ontmac;
    }
    
    /** Sets the value of the 'ontmac' field */
    public example.avro.FtthIF2.Builder setOntmac(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.ontmac = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'ontmac' field has been set */
    public boolean hasOntmac() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'ontmac' field */
    public example.avro.FtthIF2.Builder clearOntmac() {
      ontmac = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'extrainfo' field */
    public java.lang.CharSequence getExtrainfo() {
      return extrainfo;
    }
    
    /** Sets the value of the 'extrainfo' field */
    public example.avro.FtthIF2.Builder setExtrainfo(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.extrainfo = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'extrainfo' field has been set */
    public boolean hasExtrainfo() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'extrainfo' field */
    public example.avro.FtthIF2.Builder clearExtrainfo() {
      extrainfo = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    public FtthIF2 build() {
      try {
        FtthIF2 record = new FtthIF2();
        record.event_time = fieldSetFlags()[0] ? this.event_time : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.neoss_code = fieldSetFlags()[1] ? this.neoss_code : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.ifname = fieldSetFlags()[2] ? this.ifname : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.onuid = fieldSetFlags()[3] ? this.onuid : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.ontmac = fieldSetFlags()[4] ? this.ontmac : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.extrainfo = fieldSetFlags()[5] ? this.extrainfo : (java.lang.CharSequence) defaultValue(fields()[5]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
