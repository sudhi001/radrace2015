/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package domainapp.fixture.scenarios.spreadsheets;

import javax.inject.Inject;

import domainapp.dom.ingredient.IngredientRepository;
import domainapp.dom.ingredientcategory.IngredientCategory;
import domainapp.dom.ingredientcategory.IngredientCategoryRepository;
import domainapp.dom.supplier.Supplier;
import domainapp.dom.supplier.SupplierRepository;

public class CreateUsingSpreadsheetForIngredientImport<T> extends CreateUsingSpreadsheet<IngredientImport> {

    public CreateUsingSpreadsheetForIngredientImport() {
        super(IngredientImport.class);
    }

    @Override
    protected void doPersist(final IngredientImport obj) {
        final Supplier supplier =
                supplierRepository.findOrCreate(obj.getSupplier());

        final IngredientCategory ingredientCategory =
                ingredientCategoryRepository.findOrCreate(obj.getCategory());

        ingredientRepository.findOrCreate(obj.getName(), ingredientCategory, supplier);
    }

    @Inject
    IngredientRepository ingredientRepository;

    @Inject
    IngredientCategoryRepository ingredientCategoryRepository;

    @Inject
    SupplierRepository supplierRepository;

}
