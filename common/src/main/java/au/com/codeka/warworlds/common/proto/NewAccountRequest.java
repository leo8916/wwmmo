// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: account.proto at 5:1
package au.com.codeka.warworlds.common.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import okio.ByteString;

public final class NewAccountRequest extends Message<NewAccountRequest, NewAccountRequest.Builder> {
  public static final ProtoAdapter<NewAccountRequest> ADAPTER = new ProtoAdapter_NewAccountRequest();

  private static final long serialVersionUID = 0L;

  public static final String DEFAULT_EMPIRE_NAME = "";

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String empire_name;

  public NewAccountRequest(String empire_name) {
    this(empire_name, ByteString.EMPTY);
  }

  public NewAccountRequest(String empire_name, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.empire_name = empire_name;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.empire_name = empire_name;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof NewAccountRequest)) return false;
    NewAccountRequest o = (NewAccountRequest) other;
    return Internal.equals(unknownFields(), o.unknownFields())
        && Internal.equals(empire_name, o.empire_name);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (empire_name != null ? empire_name.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (empire_name != null) builder.append(", empire_name=").append(empire_name);
    return builder.replace(0, 2, "NewAccountRequest{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<NewAccountRequest, Builder> {
    public String empire_name;

    public Builder() {
    }

    public Builder empire_name(String empire_name) {
      this.empire_name = empire_name;
      return this;
    }

    @Override
    public NewAccountRequest build() {
      return new NewAccountRequest(empire_name, buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_NewAccountRequest extends ProtoAdapter<NewAccountRequest> {
    ProtoAdapter_NewAccountRequest() {
      super(FieldEncoding.LENGTH_DELIMITED, NewAccountRequest.class);
    }

    @Override
    public int encodedSize(NewAccountRequest value) {
      return (value.empire_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value.empire_name) : 0)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, NewAccountRequest value) throws IOException {
      if (value.empire_name != null) ProtoAdapter.STRING.encodeWithTag(writer, 1, value.empire_name);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public NewAccountRequest decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.empire_name(ProtoAdapter.STRING.decode(reader)); break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public NewAccountRequest redact(NewAccountRequest value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
