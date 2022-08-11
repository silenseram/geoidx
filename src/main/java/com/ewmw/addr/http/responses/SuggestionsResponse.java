package com.ewmw.addr.http.responses;

import com.ewmw.addr.models.gar.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SuggestionsResponse {

    @Getter @Setter
    List<Address> suggestions;

    public SuggestionsResponse() {
        suggestions = new ArrayList<>();
    }
}
