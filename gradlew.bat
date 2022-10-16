/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package androidx.test.espresso.remote;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import android.support.annotation.NonNull;
import androidx.test.espresso.remote.annotation.RemoteMsgField;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/**
 * Provides additional meta data about a {@link Field} declared in a class.
 *
 * <p>The data provided here is used at runtime to reflectively access fields that require proto
 * serialization.
 */
public final class FieldDescriptor {
  public final Class<?> fieldType;
  public final String fieldName;
  public final int order;

  private FieldDescriptor(@NonNull Class<?> fieldType, @NonNull String fieldName, int order) {
    this.fieldType = checkNotNull(fieldType, "fieldType cannot be null!");
    this.fieldName = checkNotNull(fieldName, "fieldName cannot be null!");
    checkState(order >= 0, "Field order must be greater then or equal to 0!");
    this.order = order;
  }

  /**
   * Creates a {@link FieldDescriptor} instance.
   *
   * @param fieldType the type of the field
   * @param fieldName the name of the field as declared in the class
   * @param order the declared order of the field, order values need to start with 0
   */
  public static FieldDescriptor of(
      @NonNull Class<?> fieldType, @NonNull String fieldName, int order) {
    return new FieldDescriptor(fieldType, fieldName, order);
  }

  /**
   * Creates a {@link FieldDescriptor} instance from a {@link Field} and {@link Annotation}.
   *
   * @param field a {@link Field} object
   * @param remoteMsgFieldAnnotation an {@link Annotation} attached to {@code field}
   */
  static FieldDescriptor of(
      @NonNull Field field, @NonNull RemoteMsgField remoteMsgFieldAnnotation) {
    checkNotNull(field, "field cannot be null");
    checkNotNull(remoteMsgFieldAnnotation, "remoteMsgFieldAnnotation cannot be null");
    return of(field.getType(), field.getName(), remoteMsgFieldA